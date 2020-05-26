using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace EventPlusAPI.ViewModel
{
    public class NotificacionesViewModel
    {
        public int Id { get; set; }
        public int IdLogin { get; set; }
        public string UserName { get; set; }
        public string NombreUsuario { get; set; }
        public string Titulo { get; set; }
        public string Mensaje { get; set; }
        public string Entregado { get; set; }
        public string Activo { get; set; }
        public DateTime FechaRegistro { get; set; }
        public string FechaRegistroMostrar { get; set; }
    }
}
