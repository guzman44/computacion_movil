using EventPlusAPI.Dao;
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


        public List<Usuario> GetAll()
        {
            return _eventPlusContext.Usuario.ToList();
        }
    }
}
