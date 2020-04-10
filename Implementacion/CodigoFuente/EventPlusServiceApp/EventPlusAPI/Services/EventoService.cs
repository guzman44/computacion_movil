using EventPlusAPI.Dao;
using EventPlusAPI.Interfaces;
using System.Collections.Generic;
using System.Linq;

namespace EventPlusAPI.Services
{
    public class EventoService : IEvento
    {
        
        public readonly EventPlusContext _eventPlusContext;


        
        public EventoService(EventPlusContext _eventPlusContext)
        {
            this._eventPlusContext = _eventPlusContext;
        }

        public List<Evento> GetAll()
        {
            return _eventPlusContext.Evento.ToList();
        }
    }
}
