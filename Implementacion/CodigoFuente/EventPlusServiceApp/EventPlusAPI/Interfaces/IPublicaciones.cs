using EventPlusAPI.Dao;
using System.Collections.Generic;

namespace EventPlusAPI.Interfaces
{
    public interface IPublicaciones
    {
        List<Publicaciones> GetAll();
    }
}
