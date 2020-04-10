using System;
using System.Collections.Generic;

namespace EventPlusAPI.Dao
{
    public partial class Categoria
    {
        public int Id { get; set; }
        public int IdCategoria { get; set; }
        public int IdLogin { get; set; }
        public int IdEvento { get; set; }
        public Boolean Activo { get; set; }
        public DateTime FechaRegistro { get; set; }

        public virtual Evento IdEventoNavigation { get; set; }
        public virtual Login IdLoginNavigation { get; set; }
    }
}
