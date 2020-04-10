using EventPlusAPI.Dao;
using EventPlusAPI.Entities;
using EventPlusAPI.Helpers;
using EventPlusAPI.Interfaces;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Options;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

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
