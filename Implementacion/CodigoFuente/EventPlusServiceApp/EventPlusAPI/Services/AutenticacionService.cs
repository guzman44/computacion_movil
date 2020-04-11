using EventPlusAPI.Dao;
using EventPlusAPI.Helpers;
using EventPlusAPI.Interfaces;
using EventPlusAPI.ViewModel;
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

        public UserTokenViewModel Authenticate(string username, string password)
        {
            var loginCorrecto = eventPlusContext.Login.Where(s => s.UserName == username && s.Password == password).FirstOrDefault();

            if (loginCorrecto == null)
                return null;

            var user = eventPlusContext.Usuario.Where(s => s.IdLogin == loginCorrecto.Id).Select(s => new UserTokenViewModel
            {
                Id = s.Id,
                IdLogin = s.IdLogin,
                FirstName = s.Nombres,
                LastName  = s.Apellidos,
                Username = s.IdLoginNavigation.UserName,
                Image = s.Imagen,
                Email = s.IdLoginNavigation.Email 
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

        public ResponseViewModel CreateAccount(CreateAccountViewModel model)
        {
            ResponseViewModel reponse = new ResponseViewModel();

            try
            {
                var emailExist = eventPlusContext.Login.Where(w => w.Email == model.Email).FirstOrDefault();

                if (emailExist != null)
                {
                    reponse.Type = "error";
                    reponse.Response = "El correo ingresado ya esta registrado.";
                }
                else
                {
                    var userNameExist = eventPlusContext.Login.Where(w => w.UserName == model.Username).FirstOrDefault();
                    if (userNameExist != null)
                    {
                        reponse.Type = "error";
                        reponse.Response = "El usuario ingresado ya esta registrado.";
                    }
                    else
                    {
                        if (!model.Password.Equals( model.RepeatPassword))
                        {
                            reponse.Type = "error";
                            reponse.Response = "La clave y la confirmacion de la clave ingresadas, no coinciden.";
                        }
                        else
                        {
                            Login account = new Login
                            {
                                Email = model.Email,
                                Activo = "1",
                                Password = model.Password,
                                UserName = model.Username
                            };

                            eventPlusContext.Login.Add(account);
                            eventPlusContext.SaveChanges();

                            Usuario user = new Usuario 
                            { 
                                Activo = "1",
                                IdLogin =account.Id,
                                Apellidos = "",
                                Nombres = ""
                            };
                            eventPlusContext.Usuario.Add(user);
                            eventPlusContext.SaveChanges();

                            reponse.Type = "success";
                            reponse.Response = "El regitsro se creo exitosamente.";
                        }                        
                    }                        
                }
                return reponse;
            }
            catch (Exception ex)
            {
                reponse.Type = "error";
                reponse.Response = "Error en el procedimiento. ---> "+ ex.Message;
                return reponse;
            }
        }

        public ResponseViewModel ChangePassword(ChangePasswordAccountViewModel model)
        {
            ResponseViewModel reponse = new ResponseViewModel();

            try
            {
                var userNameExist = eventPlusContext.Login.Where(w => w.UserName == model.Username).FirstOrDefault();
                if (userNameExist == null)
                {
                    reponse.Type = "error";
                    reponse.Response = "El usuario ingresado no existe.";
                }
                else
                {
                    var oldPassword = eventPlusContext.Login.Where(w => w.Password == model.OldPassword).FirstOrDefault();
                    if (oldPassword == null)
                    {
                        reponse.Type = "error";
                        reponse.Response = "La clave actual no coincide";
                    }
                    else
                    {
                        if (!model.NewPassword.Equals(model.RepeatNewPassword))
                        {
                            reponse.Type = "error";
                            reponse.Response = "La clave nueva y la confirmación de la clave nueva ingresadas, no coinciden.";
                        }
                        else
                        {
                            var changePassword = eventPlusContext.Login.Where(w => w.Password == model.OldPassword && w.UserName == model.Username).FirstOrDefault();
                            changePassword.Password = model.NewPassword;

                            eventPlusContext.Login.Add(changePassword);
                            eventPlusContext.Entry(changePassword).State = Microsoft.EntityFrameworkCore.EntityState.Modified;
                            eventPlusContext.SaveChanges();

                            reponse.Type = "success";
                            reponse.Response = "Cambio de clave exitoso.";
                        }
                    }
                }

                return reponse;
            }
            catch (Exception ex)
            {
                reponse.Type = "error";
                reponse.Response = "Error en el procedimiento. ---> " + ex.Message;
                return reponse;
            }

        }

        public ResponseViewModel UpdatePerfilUser(PerfilAccountViewModel model)
        {
            ResponseViewModel reponse = new ResponseViewModel();

            try
            {
                var userNameExist = eventPlusContext.Login.Where(w => w.UserName == model.Username && w.Activo == "1").FirstOrDefault();
                if (userNameExist == null)
                {
                    reponse.Type = "error";
                    reponse.Response = "El usuario ingresado no existe.";
                }
                else
                {
                    var login = eventPlusContext.Login.Where(w => w.UserName == model.Username).FirstOrDefault();
                    login.Email = model.Email;
                    eventPlusContext.Login.Add(login);
                    eventPlusContext.Entry(login).State = Microsoft.EntityFrameworkCore.EntityState.Modified;
                    eventPlusContext.SaveChanges();

                    var perfil = eventPlusContext.Usuario.Where(w => w.IdLogin == login.Id).FirstOrDefault();
                    perfil.Nombres = model.FirstName;
                    perfil.Apellidos = model.LastName;
                    perfil.Imagen = model.Image;

                    eventPlusContext.Usuario.Add(perfil);
                    eventPlusContext.Entry(perfil).State = Microsoft.EntityFrameworkCore.EntityState.Modified;
                    eventPlusContext.SaveChanges();

                    reponse.Type = "success";
                    reponse.Response = "Actualización del perfil exitosamente.";

                }

                return reponse;
            }
            catch (Exception ex)
            {
                reponse.Type = "error";
                reponse.Response = "Error en el procedimiento. ---> " + ex.Message;
                return reponse;
            }

        }
    }
}
