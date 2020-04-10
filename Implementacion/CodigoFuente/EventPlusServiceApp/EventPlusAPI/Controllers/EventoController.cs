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
        public IActionResult Create([FromBody]CreateEventoViewModel model)
        {
            var user = _eventoService.Create(model);

            return Ok(user);
        }

        /// <summary>
        /// Crea un evento asociado a un IdLogin
        /// </summary>
        /// <param name="model"></param>   
        [HttpPut("uptade")]
        public IActionResult Uptade([FromBody]UpdateEventoViewModel model)
        {
            var user = _eventoService.Uptade(model);

            return Ok(user);
        }

        /// <summary>
        /// Listado de todos los eventos
        /// </summary>
        [HttpGet("list/{idLogin}")]
        public List<AllHistoryEventoViewModel> GetAll([Required] long idLogin)
        {
            var eventos = _eventoService.GetAll(idLogin);
            return eventos;
        }

        /// <summary>
        /// Listado de todas los eventos que trae las imagenes de la galeria, publicaciones, localizacion
        /// </summary>
        [HttpGet("list/{idEvento}")]
        public AllEventoViewModel GetAllEvento([Required] long idEvento)
        {
            var evento = _eventoService.GetAllEvento(idEvento);
            return evento;
        }

        /// <summary>
        /// Listado de todas las imagenes de galeria por un evento
        /// </summary>
        [HttpGet("gallery/{idEvento}")]
        public GalleryEventoViewModel GetAllGaleriaxEvento([Required] long idEvento)
        {
            var evento = _eventoService.GetAllGaleriaxEvento(idEvento);
            return evento;
        }

        /// <summary>
        /// Listado de todas las publicaciones por un evento
        /// </summary>
        [HttpGet("publication/{idEvento}")]
        public PublicationEventoViewModel GetAllPublicacionxEvento([Required] long idEvento)
        {
            var evento = _eventoService.GetAllPublicacionxEvento(idEvento);
            return evento;
        }

        /// <summary>
        /// Listado de todas las localizaciones por un evento
        /// </summary>
        [HttpGet("location/{idEvento}")]
        public LocationEventoViewModel GetAllLocalizacionxEvento([Required] long idEvento)
        {
            var evento = _eventoService.GetAllLocalizacionxEvento(idEvento);
            return evento;
        }
    

        /// <summary>
        /// Cargar las imagenes de la galeria x una
        /// </summary>
        /// <param name="model"></param>   
        [HttpPost("gallery/id")]
        public IActionResult CreateGallery([FromBody]CreateGalleryViewModel model)
        {
            var user = _eventoService.CreateGallery(model);

            return Ok(user);
        }

        /// <summary>
        /// Cargar las imagenes de la galeria x varias
        /// </summary>
        /// <param name="model"></param>   
        [HttpPost("gallery/all")]
        public IActionResult CreateGalleryAll([FromBody]List<CreateGalleryViewModel> model)
        {
            var user = _eventoService.CreateGalleryAll(model);

            return Ok(user);
        }

        /// <summary>
        /// Crear una publicacion
        /// </summary>
        /// <param name="model"></param>   
        [HttpPost("publication")]
        public IActionResult CreatePublication([FromBody]CreatePublicationViewModel model)
        {
            var user = _eventoService.CreatePublication(model);

            return Ok(user);
        }

        /// <summary>
        /// Creacion de una localizacion x una
        /// </summary>
        /// <param name="model"></param>   
        [HttpPost("location")]
        public IActionResult CreateLocation([FromBody]CreateLocationViewModel model)
        {
            var user = _eventoService.CreateLocation(model);

            return Ok(user);
        }

        /// <summary>
        /// Creacion de una localizacion x varias
        /// </summary>
        /// <param name="model"></param>   
        [HttpPost("location")]
        public IActionResult CreateLocationAll([FromBody]List<CreateLocationViewModel> model)
        {
            var user = _eventoService.CreateLocationAll(model);

            return Ok(user);
        }



    }
}
