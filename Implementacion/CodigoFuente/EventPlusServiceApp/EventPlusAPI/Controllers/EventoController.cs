using EventPlusAPI.Interfaces;
using EventPlusAPI.Dao;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using System.Collections.Generic;
using System.Threading.Tasks;

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

        [HttpGet]
        public List<Evento> GetAll()
        {
            var eventos = _eventoService.GetAll();
            return eventos;
        }
    }
}
