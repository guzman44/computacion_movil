using EventPlusAPI.Dao;
using EventPlusAPI.ViewModel;
using EventPlusAPI.Interfaces;
using System.Collections.Generic;
using System.Linq;

namespace EventPlusAPI.Services
{
    public class UsuarioService : IUsuario
    {
        public readonly EventPlusContext _eventPlusContext;

        public UsuarioService(EventPlusContext _eventPlusContext)
        {
            this._eventPlusContext = _eventPlusContext;
        }


        public List<UserViewModel> GetAll()
        {
            return _eventPlusContext.Usuario.Select(s => new UserViewModel
            {
                Id = s.Id,
                FirstName = s.Nombres,
                LastName = s.Apellidos,
                Username = s.IdLoginNavigation.UserName
            }).ToList();
        }

        public UserViewModel GetbyId(long id)
        {
            return _eventPlusContext.Usuario.Where(s => s.Id == id).Select(s => new UserViewModel
            {
                Id = s.Id,
                FirstName = s.Nombres,
                LastName = s.Apellidos,
                Username = s.IdLoginNavigation.UserName
            }).FirstOrDefault();
        }

        public UserViewModel GetbyUsername(string username)
        {
            var login = _eventPlusContext.Login.Where(s => s.UserName == username).FirstOrDefault();

            return _eventPlusContext.Usuario.Where(s => s.IdLogin == login.Id).Select(s => new UserViewModel
            {
                Id = s.Id,
                FirstName = s.Nombres,
                LastName = s.Apellidos,
                Username = s.IdLoginNavigation.UserName
            }).FirstOrDefault();
        }
    }
}
