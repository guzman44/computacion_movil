using System;
using System.Collections.Generic;

namespace EventPlusAPI.Dao
{
    public partial class Localizacion
    {
        public int Id { get; set; }
        public int IdEvento { get; set; }
        public string Latitud { get; set; }
        public string Longitud { get; set; }
        public string Direccion { get; set; }
        public string Comentario { get; set; }
        public string Activo { get; set; }
        public DateTime FechaRegistro { get; set; }

        public virtual Evento IdEventoNavigation { get; set; }
    }
}
