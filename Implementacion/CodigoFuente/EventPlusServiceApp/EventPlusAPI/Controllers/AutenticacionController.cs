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

        [AllowAnonymous]
        [HttpPost("authenticate")]
        public IActionResult Authenticate([FromBody]AuthenticateModel model)
        {
            var user = _autenticacion.Authenticate(model.Username, model.Password);

            if (user == null)
                return BadRequest(new { message = "Nombre de usuario o contraseña incorrectos" });

            return Ok(user);
        }

        [HttpGet]
        public IActionResult GetAll()
        {
            var users = _autenticacion.GetAll();
            return Ok(users);
        }
    }
}
