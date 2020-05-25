using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace EventPlusAPI.ViewModel
{
    public class PublicationViewModel
    {

        public int Id { get; set; }
        public int IdLogin { get; set; }
        public string UserName { get; set; }
        public string Nombre { get; set; }
        public int IdEvento { get; set; }
        public string Evento { get; set; }
        public string Comentario { get; set; }
        public DateTime FechaIngreso { get; set; }
        public string FechaIngresoMostrar { get; set; }
        public byte[] Imagen { get; set; }
        public byte[] Avatar { get; set; }
        public string Activo { get; set; }
    }
}
