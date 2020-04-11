using EventPlusAPI.Dao;
using EventPlusAPI.Interfaces;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace EventPlusAPI.Services
{
    public class PublicacionesService : IPublicaciones
    {
        public readonly EventPlusContext _eventPlusContext;

        public PublicacionesService(EventPlusContext _eventPlusContext)
        {
            this._eventPlusContext = _eventPlusContext;
        }


        public List<Publicaciones> GetAll()
        {
            return _eventPlusContext.Publicaciones.ToList();
        }
    }
}
