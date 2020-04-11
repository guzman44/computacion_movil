using System;
using System.Collections.Generic;

namespace EventPlusAPI.Dao
{
    public partial class Galeria
    {
        public int Id { get; set; }
        public byte[] Image { get; set; }
        public int IdEvento { get; set; }
        public DateTime FechaRegistro { get; set; }

        public virtual Evento IdEventoNavigation { get; set; }
    }
}
