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
        
    }


    public class CreateEventoViewModel
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
        public string FechaInicio { get; set; }
        [Required]
        public string FechaFin { get; set; }

        public DateTime FechaInicioG { get; set; }
        public DateTime FechaFinG { get; set; }
        [Required]
        public int IdTipo { get; set; }
        public List<Localizacion> Localizacion { get; set; }
    }

    public class UpdateEventoViewModel
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
    }

    public class AllHistoryEventoViewModel
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
        public string NombreUsuario { get; set; }
        public Boolean Like { get; set; }
        public int Likes { get; set; }
        public DateTime FechaRegistro { get; set; }
        public List<Localizacion> Localizacion { get; set; }
    }


    public class AllEventoViewModel
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
        public string NombreUsuario { get; set; }
        public Boolean Like { get; set; }
        public int Likes { get; set; }
        public DateTime FechaRegistro { get; set; }

        public List<Galeria> Galeria { get; set; }
        public List<Localizacion> Localizacion { get; set; }
        public List<Publicaciones> Publicaciones { get; set; }
    }

    public class GalleryEventoViewModel
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
        public string NombreUsuario { get; set; }
        public Boolean Like { get; set; }
        public int Likes { get; set; }
        public DateTime FechaRegistro { get; set; }

        public List<Galeria> Galeria { get; set; }
    }
    public class LocationEventoViewModel
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
        public string NombreUsuario { get; set; }
        public Boolean Like { get; set; }
        public int Likes { get; set; }
        public DateTime FechaRegistro { get; set; }

        public List<Localizacion> Localizacion { get; set; }
    }

    public class PublicationEventoViewModel
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
        public string NombreUsuario { get; set; }
        public Boolean Like { get; set; }
        public int Likes { get; set; }
        public DateTime FechaRegistro { get; set; }

        public List<Publicaciones> Publicaciones { get; set; }
    }


    public class CreateGalleryViewModel
    {
        public int Id { get; set; }
        public int IdLogin { get; set; }
        public byte[] Image { get; set; }
        public int IdEvento { get; set; }
    }


    public class CreatePublicationViewModel
    {
        public int Id { get; set; }
        public int IdLogin { get; set; }
        public int IdEvento { get; set; }
        public string Comentario { get; set; }
        public DateTime FechaIngreso { get; set; }
        public byte[] Imagen { get; set; }
    }


    public class CreateLocationViewModel
    {
        public int Id { get; set; }
        public int IdLogin { get; set; }
        public int IdEvento { get; set; }
        public string Latitud { get; set; }
        public string Longitud { get; set; }
        public string Direccion { get; set; }
        public string Comentario { get; set; }
    }

    public class SearchEventLoginViewModel
    {
        public int IdLogin { get; set; }
        public string Text { get; set; }
    }

    public class SearchEventAllViewModel
    {
        public string Text { get; set; }
    }

}
