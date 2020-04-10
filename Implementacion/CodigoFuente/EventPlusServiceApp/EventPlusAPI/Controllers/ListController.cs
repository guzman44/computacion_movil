using EventPlusAPI.Interfaces;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using System.ComponentModel.DataAnnotations;

namespace EventPlusAPI.Controllers
{
    [Authorize]
    [ApiController]
    [Route("api/[controller]")]
    public class ListController : ControllerBase
    {
        private ISelect Select;

        public ListController(ISelect Select)
        {
            this.Select = Select;
        }

        /// <summary>
        /// Obtiene el Select por el tipo de valor
        /// </summary>
        /// <remarks>
        /// 
        ///  Select Tipo Valor = CAT_EVENTO, CAT_HISTORICO
        /// 
        /// </remarks>
        /// param name="tipoValor"
        [HttpGet("{tipoValor}")]
        public IActionResult GetbyTipoValor([Required] string tipoValor)
        {
            var select = Select.GetbyTipoValor(tipoValor);
            return Ok(select);
        }

    }
}