using EventPlusAPI.Dao;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace EventPlusAPI.Interfaces
{
    public interface IPublicaciones
    {
        List<Publicaciones> GetAll();
    }
}
