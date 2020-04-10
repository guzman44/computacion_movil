using System;
using System.Collections.Generic;

namespace EventPlusAPI.Dao
{
    public partial class Localizacion
    {
        public int Id { get; set; }
        public int IdEvento { get; set; }
        public decimal Latitud { get; set; }
        public decimal Longitud { get; set; }
        public string Direccion { get; set; }
        public string Comentario { get; set; }
        public string Activo { get; set; }

        public virtual Evento IdEventoNavigation { get; set; }
    }
}
