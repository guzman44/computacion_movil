using System;
using System.Collections.Generic;

namespace EventPlusAPI.Dao
{
    public partial class Publicaciones
    {
        public int Id { get; set; }
        public int IdLogin { get; set; }
        public int IdEvento { get; set; }
        public string Comentario { get; set; }
        public DateTime FechaIngreso { get; set; }
        public byte[] Imagen { get; set; }
        public string Activo { get; set; }
    }
}
