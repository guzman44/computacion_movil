using System;
using System.Collections.Generic;

namespace EventPlusAPI.Dao
{
    public partial class EventoUsuario
    {
        public int IdEvento { get; set; }
        public int IdLogin { get; set; }

        public virtual Evento IdEventoNavigation { get; set; }
        public virtual Login IdLoginNavigation { get; set; }
    }
}
