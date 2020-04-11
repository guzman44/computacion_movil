using System;
using System.Collections.Generic;

namespace EventPlusAPI.Dao
{
    public partial class Usuario
    {
        public int Id { get; set; }
        public string Nombres { get; set; }
        public string Apellidos { get; set; }
        public byte[] Imagen { get; set; }
        public int IdLogin { get; set; }
        public string Activo { get; set; }

        public virtual Login IdLoginNavigation { get; set; }
    }
}
