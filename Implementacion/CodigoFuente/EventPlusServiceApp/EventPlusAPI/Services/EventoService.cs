using EventPlusAPI.Dao;
using EventPlusAPI.Entities;
using EventPlusAPI.Helpers;
using EventPlusAPI.Interfaces;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Options;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace EventPlusAPI.Services
{
    public class EventoService : IEventoService
    {
        private readonly AppSettings _appSettings;
        private readonly EventPlusContext _eventPlusContext;

        public EventoService(IOptions<AppSettings> appSettings)
        {
            _appSettings = appSettings.Value;
        }

        public async Task<List<Evento>> GetAll()
        {
            return await _eventPlusContext.Evento.ToListAsync();
        }
    }
}
