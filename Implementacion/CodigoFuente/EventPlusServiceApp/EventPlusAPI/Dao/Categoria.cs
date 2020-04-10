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
        public string Activo { get; set; }
        public DateTime FechaRegistro { get; set; }
    }
}
