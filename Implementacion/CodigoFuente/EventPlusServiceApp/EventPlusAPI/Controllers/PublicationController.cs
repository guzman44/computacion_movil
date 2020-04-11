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
        public List<Publicaciones> GetAll()
        {
            var eventos = _publicacionesService.GetAll();
            return eventos;
        }

    }
}