using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using EventPlusAPI.Interfaces;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace EventPlusAPI.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class UsuarioController : ControllerBase
    {
        private IUsuario Usuario;

        public UsuarioController(IUsuario Usuario)
        {
            this.Usuario = Usuario;
        }
    }
}