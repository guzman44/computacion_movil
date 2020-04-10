using System;
using System.Collections.Generic;

namespace EventPlusAPI.Dao
{
    public partial class Evento
    {
        public int Id { get; set; }
        public string Nombre { get; set; }
        public string Descripcion { get; set; }
        public byte[] Imagen { get; set; }
        public DateTime FechaInicio { get; set; }
        public DateTime FechaFin { get; set; }
        public string Activo { get; set; }
    }
}
