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
    public class EventController : ControllerBase
    {
        private IEvento _eventoService;

        public EventController(IEvento eventoService)
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
        [HttpPost("location/all")]
        public IActionResult CreateLocationAll([FromBody]List<CreateLocationViewModel> model)
        {
            var user = _eventoService.CreateLocationAll(model);

            return Ok(user);
        }

        /// <summary>
        /// Creacion de un like a un usuario x evento
        /// </summary>
        /// param name="idEvent"
        /// param name="idLogin" 
        [HttpPost("like/{idEvent}/{idLogin}")]
        public IActionResult CreateLike([Required]int idEvent, [Required]int idLogin)
        {
            var user = _eventoService.CreateLike(idEvent, idLogin);

            return Ok(user);
        }

        /// <summary>
        /// Eliminacion de una like de un evento por usuario
        /// </summary>
        /// param name="idEvent"
        /// param name="idLogin"
        [HttpDelete("like/{idEvent}/{idLogin}")]
        public IActionResult DeleteLike([Required]int idEvent, [Required]int idLogin)
        {
            var user = _eventoService.DeleteLike(idEvent, idLogin);

            return Ok(user);
        }

        /// <summary>
        /// Eliminacion de una like de una publicacion por usuario
        /// </summary>
        /// param name="idEvent"
        /// param name="idLogin"
        [HttpDelete("publication/{idEvent}/{idLogin}")]
        public IActionResult DeletePublication([Required]int idEvent, [Required]int idLogin)
        {
            var user = _eventoService.DeletePublication(idEvent, idLogin);

            return Ok(user);
        }

        /// <summary>
        /// Eliminacion de una like de un evento por usuario
        /// </summary>
        /// param name="idEvent"
        /// param name="idLogin"
        [HttpDelete("location/{idEvent}/{idLogin}")]
        public IActionResult DeleteLocation([Required]int idEvent, [Required]int idLogin)
        {
            var user = _eventoService.DeleteLocation(idEvent, idLogin);

            return Ok(user);
        }

        /// <summary>
        /// Realiza busqueda por el nombre del evento los que tenga asociado el login
        /// </summary>
        /// <param name="model"></param>   
        [HttpPost("search/login")]
        public IActionResult SearchEventLogin([FromBody]SearchEventLoginViewModel model)
        {
            var user = _eventoService.SearchEventLogin(model);

            return Ok(user);
        }

        /// <summary>
        /// Realiza busqueda por el nombre del evento en toda la tabla de evento no importa el login, este es para la busqueda del panel como facebook
        /// </summary>
        /// <param name="model"></param>   
        [HttpPost("search/all")]
        public IActionResult SearchEventAll([FromBody]SearchEventAllViewModel model)
        {
            var user = _eventoService.SearchEventAll(model);

            return Ok(user);
        }



    }
}
