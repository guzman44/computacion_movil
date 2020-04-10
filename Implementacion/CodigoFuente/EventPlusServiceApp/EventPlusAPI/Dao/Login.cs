using System;
using System.Collections.Generic;

namespace EventPlusAPI.Dao
{
    public partial class Login
    {
        public Login()
        {
            Categoria = new HashSet<Categoria>();
            EventoUsuario = new HashSet<EventoUsuario>();
            Publicaciones = new HashSet<Publicaciones>();
            Usuario = new HashSet<Usuario>();
        }

        public int Id { get; set; }
        public string UserName { get; set; }
        public string Password { get; set; }
        public string Email { get; set; }
        public string Activo { get; set; }
        public DateTime FechaRegistro { get; set; }

        public virtual ICollection<Categoria> Categoria { get; set; }
        public virtual ICollection<EventoUsuario> EventoUsuario { get; set; }
        public virtual ICollection<Publicaciones> Publicaciones { get; set; }
        public virtual ICollection<Usuario> Usuario { get; set; }
    }
}
