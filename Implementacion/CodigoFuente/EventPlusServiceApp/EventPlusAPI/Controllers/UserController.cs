using EventPlusAPI.Interfaces;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using System.ComponentModel.DataAnnotations;

namespace EventPlusAPI.Controllers
{
    [Authorize]
    [ApiController]
    [Route("api/[controller]")]
    public class UserController : ControllerBase
    {
        private IUsuario Usuario;

        public UserController(IUsuario Usuario)
        {
            this.Usuario = Usuario;
        }

        /// <summary>
        /// Listado de todos los usuarios
        /// </summary>
        [HttpGet("All")]
        public IActionResult GetAll()
        {
            var users = Usuario.GetAll();
            return Ok(users);
        }

        /// <summary>
        /// Obtiene el usuario por Id
        /// </summary>
        /// param name="id"
        [HttpGet("id/{id}")]
        public IActionResult GetbyId([Required] long id)
        {
            var user = Usuario.GetbyId(id);
            return Ok(user);
        }

        /// <summary>
        /// Obtiene el usuario por usernema
        /// </summary>
        /// param name="username"
        [HttpGet("username/{username}")]
        public IActionResult GetbyUsername([Required] string username)
        {
            var user = Usuario.GetbyUsername(username);
            return Ok(user);
        }

    }
}