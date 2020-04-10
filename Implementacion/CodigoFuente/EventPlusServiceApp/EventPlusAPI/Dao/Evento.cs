using System;
using System.Collections.Generic;

namespace EventPlusAPI.Dao
{
    public partial class Evento
    {
        public Evento()
        {
            Categoria = new HashSet<Categoria>();
            EventoUsuario = new HashSet<EventoUsuario>();
            Localizacion = new HashSet<Localizacion>();
            Publicaciones = new HashSet<Publicaciones>();
        }

        public int Id { get; set; }
        public string Nombre { get; set; }
        public string Descripcion { get; set; }
        public byte[] Imagen { get; set; }
        public DateTime FechaInicio { get; set; }
        public DateTime FechaFin { get; set; }
        public string Activo { get; set; }

        public virtual ICollection<Categoria> Categoria { get; set; }
        public virtual ICollection<EventoUsuario> EventoUsuario { get; set; }
        public virtual ICollection<Localizacion> Localizacion { get; set; }
        public virtual ICollection<Publicaciones> Publicaciones { get; set; }
    }
}
