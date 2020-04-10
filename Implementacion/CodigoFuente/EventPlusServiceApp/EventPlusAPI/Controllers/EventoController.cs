using EventPlusAPI.Dao;
using EventPlusAPI.Interfaces;
using EventPlusAPI.ViewModel;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

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
        /// Crea un evento asociado a un IdLogin
        /// </summary>
        /// <param name="model"></param>   
        [HttpPost("create")]
        public IActionResult Create([FromBody]EventoViewModel model)
        {
            var user = _eventoService.Create(model);

            return Ok(user);
        }

        /// <summary>
        /// Crea un evento asociado a un IdLogin
        /// </summary>
        /// <param name="model"></param>   
        [HttpPut("uptade")]
        public IActionResult Uptade([FromBody]EventoViewModel model)
        {
            var user = _eventoService.Uptade(model);

            return Ok(user);
        }

        /// <summary>
        /// Listado de todos los eventos
        /// </summary>
        [HttpGet("list/{idLogin}")]
        public List<EventoViewModel> GetAll([Required] long idLogin)
        {
            var eventos = _eventoService.GetAll(idLogin);
            return eventos;
        }

        /// <summary>
        /// Listado de todas los eventos que trae las imagenes de la galeria, publicaciones, localizacion
        /// </summary>
        [HttpGet("list/{idEvento}")]
        public EventoViewModel GetAllEvento([Required] long idEvento)
        {
            var evento = _eventoService.GetAllEvento(idEvento);
            return evento;
        }

        /// <summary>
        /// Listado de todas las imagenes de galeria por un evento
        /// </summary>
        [HttpGet("gallery/{idEvento}")]
        public EventoViewModel GetAllGaleriaxEvento([Required] long idEvento)
        {
            var evento = _eventoService.GetAllGaleriaxEvento(idEvento);
            return evento;
        }


        /// <summary>
        /// Listado de todas las publicaciones por un evento
        /// </summary>
        [HttpGet("publication/{idEvento}")]
        public EventoViewModel GetAllPublicacionxEvento([Required] long idEvento)
        {
            var evento = _eventoService.GetAllPublicacionxEvento(idEvento);
            return evento;
        }

        /// <summary>
        /// Listado de todas las localizaciones por un evento
        /// </summary>
        [HttpGet("location/{idEvento}")]
        public EventoViewModel GetAllLocalizacionxEvento([Required] long idEvento)
        {
            var evento = _eventoService.GetAllLocalizacionxEvento(idEvento);
            return evento;
        }
    }
}
