using EventPlusAPI.Interfaces;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace EventPlusAPI.Controllers
{
    [Authorize]
    [ApiController]
    [Route("api/[controller]")]
    public class UsuarioController : ControllerBase
    {
        private IUsuario Usuario;

        public UsuarioController(IUsuario Usuario)
        {
            this.Usuario = Usuario;
        }

        /// <summary>
        /// Listado de todos los usuarios
        /// </summary>
        [HttpGet]
        public IActionResult GetAll()
        {
            var users = Usuario.GetAll();
            return Ok(users);
        }

    }
}