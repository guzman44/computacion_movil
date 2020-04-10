using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using EventPlusAPI.Dao;
using EventPlusAPI.Interfaces;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace EventPlusAPI.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class PublicacionesController : ControllerBase
    {
        IPublicaciones PublicacionesService;

        public PublicacionesController(IPublicaciones PublicacionesService)
        {
            this.PublicacionesService = PublicacionesService;
        }
        public List<Publicaciones> GetAll()
        {
            var eventos = PublicacionesService.GetAll();
            return eventos;
        }

    }
}