using EventPlusAPI.Dao;
using EventPlusAPI.Interfaces;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using System.Collections.Generic;

namespace EventPlusAPI.Controllers
{
    [Authorize]
    [ApiController]
    [Route("api/[controller]")]
    public class EventoController : ControllerBase
    {
        private IEvento _eventoService;

        public EventoController(IEvento eventoService)
        {
            _eventoService = eventoService;
        }

        /// <summary>
        /// Listado de todos los eventos
        /// </summary>
        [HttpGet]
        public List<Evento> GetAll()
        {
            var eventos = _eventoService.GetAll();
            return eventos;
        }
    }
}
