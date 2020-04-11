using System;
using System.Collections.Generic;

namespace EventPlusAPI.Dao
{
    public partial class ParametrizacionObjetos
    {
        public ParametrizacionObjetos()
        {
            Evento = new HashSet<Evento>();
        }

        public int Id { get; set; }
        public string Nombre { get; set; }
        public string Valor { get; set; }
        public string Activo { get; set; }

        public virtual ICollection<Evento> Evento { get; set; }
    }
}
