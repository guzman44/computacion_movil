using System;
using System.Collections.Generic;

namespace EventPlusAPI.Dao
{
    public partial class Notificaciones
    {
        public int Id { get; set; }
        public int IdLogin { get; set; }
        public string Titulo { get; set; }
        public string Mensaje { get; set; }
        public string Entregado { get; set; }
        public string Activo { get; set; }
        public DateTime FechaRegistro { get; set; }

        public virtual Login IdLoginNavigation { get; set; }
    }
}
