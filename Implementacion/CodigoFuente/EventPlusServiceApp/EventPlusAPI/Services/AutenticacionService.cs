using EventPlusAPI.Dao;
using EventPlusAPI.Dtos;
using EventPlusAPI.Helpers;
using EventPlusAPI.Interfaces;
using Microsoft.Extensions.Options;
using Microsoft.IdentityModel.Tokens;
using System;
using System.IdentityModel.Tokens.Jwt;
using System.Linq;
using System.Security.Claims;
using System.Text;

namespace EventPlusAPI.Services
{
    public class AutenticacionService : IAutenticacion
    {
        private readonly AppSettings _appSettings;
        private readonly EventPlusContext eventPlusContext;

        public AutenticacionService(IOptions<AppSettings> appSettings, EventPlusContext _eventPlusContext)
        {
            _appSettings = appSettings.Value;
            this.eventPlusContext = _eventPlusContext;
        }

        public UserEntity Authenticate(string username, string password)
        {
            var loginCorrecto = eventPlusContext.Login.Where(s => s.UserName == username && s.Password == password).FirstOrDefault();

            if (loginCorrecto == null)
                return null;

            var user = eventPlusContext.Usuario.Where(s => s.IdLogin == loginCorrecto.Id).Select(s => new UserEntity
            {
                Id = s.Id,
                FirstName = s.Nombres,
                LastName  = s.Apellidos,
                Username = s.IdLoginNavigation.UserName
            }).FirstOrDefault();

            var tokenHandler = new JwtSecurityTokenHandler();
            var key = Encoding.ASCII.GetBytes(_appSettings.Secret);
            var tokenDescriptor = new SecurityTokenDescriptor
            {
                Subject = new ClaimsIdentity(new Claim[]
                {
                    new Claim(ClaimTypes.Name, user.Id.ToString())
                }),
                Expires = DateTime.UtcNow.AddDays(7),
                SigningCredentials = new SigningCredentials(new SymmetricSecurityKey(key), SecurityAlgorithms.HmacSha256Signature)
            };
            var token = tokenHandler.CreateToken(tokenDescriptor);
            user.Token = tokenHandler.WriteToken(token);

            return user.WithoutPassword();
        }
      
    }
}
