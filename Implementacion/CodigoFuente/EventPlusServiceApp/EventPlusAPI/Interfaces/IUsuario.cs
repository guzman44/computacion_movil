using EventPlusAPI.Dao;
using System.Collections.Generic;

namespace EventPlusAPI.Interfaces
{
    public interface IUsuario
    {
        List<Usuario> GetAll();
    }
}
