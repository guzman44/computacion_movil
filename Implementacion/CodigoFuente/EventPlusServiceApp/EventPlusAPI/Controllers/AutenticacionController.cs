using EventPlusAPI.Interfaces;
using EventPlusAPI.Models;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace EventPlusAPI.Controllers
{
    [Authorize]
    [ApiController]
    [Route("api/[controller]")]
    public class AutenticacionController : ControllerBase
    {
        private IAutenticacion _autenticacion;

        public AutenticacionController(IAutenticacion autenticacion)
        {
            _autenticacion = autenticacion;
        }
        /// <summary>
        /// Autenticacion de la aplicación
        /// </summary>
        /// <param name="model"></param>   
        [AllowAnonymous]
        [HttpPost("login")]
        public IActionResult Authenticate([FromBody]AuthenticateModel model)
        {
            var user = _autenticacion.Authenticate(model.Username, model.Password);

            if (user == null)
                return BadRequest(new { message = "Nombre de usuario o contraseña incorrectos" });

            return Ok(user);
        }

        
    }
}
