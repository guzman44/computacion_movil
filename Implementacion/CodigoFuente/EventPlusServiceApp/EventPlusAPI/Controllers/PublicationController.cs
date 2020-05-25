using EventPlusAPI.Dao;
using EventPlusAPI.Interfaces;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using System.Collections.Generic;
using EventPlusAPI.ViewModel;

namespace EventPlusAPI.Controllers
{
    [Authorize]
    [ApiController]
    [Route("api/[controller]")]
    public class PublicationController : ControllerBase
    {
        IPublicaciones _publicacionesService;

        public PublicationController(IPublicaciones PublicacionesService)
        {
            this._publicacionesService = PublicacionesService;
        }

        /// <summary>
        /// Listado de todos las publicaciones
        /// </summary>
        [HttpGet]
        public List<PublicationViewModel> GetAll()
        {
            var eventos = _publicacionesService.GetAll();
            return eventos;
        }

        /// <summary>
        /// Listado de todos las notificaciones por usuario
        /// </summary>
        [HttpGet("notificacion/list/{idLogin}")]
        public List<NotificacionesViewModel> GetAllNotificaciones(int idLogin)
        {
            var eventos = _publicacionesService.GetAllNotificaciones(idLogin);
            return eventos;
        }

        /// <summary>
        /// Crea una notificacion asociada a un IdLogin
        /// </summary>
        /// <param name="model"></param>   
        [HttpPost("notificacion/create")]
        public IActionResult CreateNotificacion([FromBody]NotificacionesViewModel model)
        {
            var noti = _publicacionesService.CreateNotificacion(model);

            return Ok(noti);
        }


        /// <summary>
        /// Actualiza una notificacion por id
        /// </summary>
        /// <param name="model"></param>   
        [HttpPut("notificacion/uptade")]
        public IActionResult UptadeNotificacion([FromBody]NotificacionesViewModel model)
        {
            var user = _publicacionesService.UptadeNotificacion(model);

            return Ok(user);
        }


    }
}