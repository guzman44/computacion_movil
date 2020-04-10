using EventPlusAPI.Dao;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace EventPlusAPI.ViewModel
{
    public class EventoViewModel
    {
        public int Id { get; set; }
        [Required]
        public int IdLogin { get; set; }
        [Required]
        public string Nombre { get; set; }
        [Required]
        public string Descripcion { get; set; }
        public byte[] ImagenMiniatura { get; set; }
        [Required]
        public DateTime FechaInicio { get; set; }
        [Required]
        public DateTime FechaFin { get; set; }
        [Required]
        public int IdTipo { get; set; }
        public string Tipo { get; set; }

        public List<Galeria> Galeria { get; set; }
        public List<Localizacion> Localizacion { get; set; }
        public List<Publicaciones> Publicaciones { get; set; }
    }

}
