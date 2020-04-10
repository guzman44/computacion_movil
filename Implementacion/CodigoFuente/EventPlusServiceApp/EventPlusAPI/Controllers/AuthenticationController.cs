using EventPlusAPI.Interfaces;
using EventPlusAPI.ViewModel;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace EventPlusAPI.Controllers
{
    [Authorize]
    [ApiController]
    [Route("api/[controller]")]
    public class AuthenticationController : ControllerBase
    {
        private IAutenticacion _autenticacion;

        public AuthenticationController(IAutenticacion autenticacion)
        {
            _autenticacion = autenticacion;
        }
        /// <summary>
        /// Autenticacion de la aplicación
        /// </summary>
        /// <param name="model"></param>   
        [AllowAnonymous]
        [HttpPost("login")]
        public IActionResult Authenticate([FromBody]AuthenticateViewModel model)
        {
            var user = _autenticacion.Authenticate(model.Username, model.Password);

            if (user == null)
                return BadRequest(new { message = "Nombre de usuario o contraseña incorrectos" });

            return Ok(user);
        }


        /// <summary>
        /// Registrar una nueva cuenta 
        /// </summary>
        /// <param name="model"></param>   
        [AllowAnonymous]
        [HttpPost("create/acount")]
        public IActionResult CreateAccount([FromBody]CreateAccountViewModel model)
        {
            var user = _autenticacion.CreateAccount(model);

            return Ok(user);
        }

        /// <summary>
        /// Cambio de contraseña de un usuario
        /// </summary>
        /// <param name="model"></param>   
        [HttpPost("change/password")]
        public IActionResult ChangePassword([FromBody]ChangePasswordAccountViewModel model)
        {
            var user = _autenticacion.ChangePassword(model);

            return Ok(user);
        }

        /// <summary>
        /// Actualizacion de un usuario
        /// </summary>
        /// <param name="model"></param>   
        [HttpPost("update/perfilUser")]
        public IActionResult UpdatePerfilUser([FromBody]PerfilAccountViewModel model)
        {
            var user = _autenticacion.UpdatePerfilUser(model);

            return Ok(user);
        }


    }
}
