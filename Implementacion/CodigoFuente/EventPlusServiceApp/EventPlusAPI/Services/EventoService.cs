using EventPlusAPI.Dao;
using EventPlusAPI.Interfaces;
using EventPlusAPI.ViewModel;
using System;
using System.Collections.Generic;
using System.Linq;

namespace EventPlusAPI.Services
{
    public class EventoService : IEvento
    {        
        public readonly EventPlusContext _eventPlusContext;

        public EventoService(EventPlusContext _eventPlusContext)
        {
            this._eventPlusContext = _eventPlusContext;
        }

        public ResponseViewModel Create(EventoViewModel model)
        {
            ResponseViewModel reponse = new ResponseViewModel();

            try
            {
                Evento ev = new Evento
                {
                    Nombre = model.Nombre,  
                    
                    Descripcion = model.Descripcion,
                    Imagen = model.ImagenMiniatura,
                    FechaInicio = model.FechaInicio,
                    FechaFin = model.FechaFin,
                    Activo = "1",
                    IdTipo = model.IdTipo
                };
                _eventPlusContext.Evento.Add(ev);
                _eventPlusContext.SaveChanges();

                EventoUsuario evUs = new EventoUsuario
                {
                    IdEvento = ev.Id,
                    IdLogin = model.IdLogin
                };
                _eventPlusContext.EventoUsuario.Add(evUs);
                _eventPlusContext.SaveChanges();

                reponse.Type = "success";
                reponse.Response = "El regitsro se creo exitosamente.";

                return reponse;
            }
            catch (Exception ex)
            {
                reponse.Type = "error";
                reponse.Response = "Error en el procedimiento. ---> " + ex.Message;
                return reponse;
            }

        }

        public List<EventoViewModel> GetAll(long idLogin)
        {
            var listEvent = _eventPlusContext.EventoUsuario.Where(w => w.IdLogin == idLogin).Select(s => new EventoViewModel
            {
                Id = s.IdEventoNavigation.Id,
                IdLogin = s.IdLogin,
                Nombre = s.IdEventoNavigation.Nombre,
                Descripcion = s.IdEventoNavigation.Descripcion,
                FechaFin = s.IdEventoNavigation.FechaFin,
                FechaInicio = s.IdEventoNavigation.FechaInicio,
                IdTipo = s.IdEventoNavigation.IdTipo,
                Tipo = s.IdEventoNavigation.IdTipoNavigation.Nombre,
                ImagenMiniatura = s.IdEventoNavigation.Imagen
            }).ToList();

            return listEvent;
        }

        public EventoViewModel GetAllEvento(long idEvento)
        {
            var evento = _eventPlusContext.Evento.Where(w => w.Id == idEvento).Select(s => new EventoViewModel
            {
                Id = s.Id,
                IdLogin = _eventPlusContext.EventoUsuario.Where(s => s.IdEvento == idEvento).Select(s => s.IdLogin).FirstOrDefault(),
                Nombre = s.Nombre,
                Descripcion = s.Descripcion,
                FechaFin = s.FechaFin,
                FechaInicio = s.FechaInicio,
                IdTipo = s.IdTipo,
                Tipo = s.IdTipoNavigation.Nombre,
                ImagenMiniatura = s.Imagen,
                Galeria = _eventPlusContext.Galeria.Where(s => s.IdEvento == s.Id).ToList(),
                Localizacion = _eventPlusContext.Localizacion.Where(s => s.IdEvento == s.Id).ToList(),
                Publicaciones = _eventPlusContext.Publicaciones.Where(s => s.IdEvento == s.Id).ToList()
            }).FirstOrDefault();

            return evento;
        }

        public EventoViewModel GetAllGaleriaxEvento(long idEvento)
        {
            var evento = _eventPlusContext.Evento.Where(w => w.Id == idEvento).Select(s => new EventoViewModel
            {
                Id = s.Id,
                IdLogin = _eventPlusContext.EventoUsuario.Where(s => s.IdEvento == idEvento).Select(s => s.IdLogin).FirstOrDefault(),
                Nombre = s.Nombre,
                Descripcion = s.Descripcion,
                FechaFin = s.FechaFin,
                FechaInicio = s.FechaInicio,
                IdTipo = s.IdTipo,
                Tipo = s.IdTipoNavigation.Nombre,
                ImagenMiniatura = s.Imagen,
                Galeria = _eventPlusContext.Galeria.Where(s => s.IdEvento == s.Id).ToList()
            }).FirstOrDefault();

            return evento;
        }

        public EventoViewModel GetAllLocalizacionxEvento(long idEvento)
        {
            var evento = _eventPlusContext.Evento.Where(w => w.Id == idEvento).Select(s => new EventoViewModel
            {
                Id = s.Id,
                IdLogin = _eventPlusContext.EventoUsuario.Where(s => s.IdEvento == idEvento).Select(s => s.IdLogin).FirstOrDefault(),
                Nombre = s.Nombre,
                Descripcion = s.Descripcion,
                FechaFin = s.FechaFin,
                FechaInicio = s.FechaInicio,
                IdTipo = s.IdTipo,
                Tipo = s.IdTipoNavigation.Nombre,
                ImagenMiniatura = s.Imagen,
                Localizacion = _eventPlusContext.Localizacion.Where(s => s.IdEvento == s.Id).ToList()
            }).FirstOrDefault();

            return evento;
        }

        public EventoViewModel GetAllPublicacionxEvento(long idEvento)
        {
            var evento = _eventPlusContext.Evento.Where(w => w.Id == idEvento).Select(s => new EventoViewModel
            {
                Id = s.Id,
                IdLogin = _eventPlusContext.EventoUsuario.Where(s => s.IdEvento == idEvento).Select(s => s.IdLogin).FirstOrDefault(),
                Nombre = s.Nombre,
                Descripcion = s.Descripcion,
                FechaFin = s.FechaFin,
                FechaInicio = s.FechaInicio,
                IdTipo = s.IdTipo,
                Tipo = s.IdTipoNavigation.Nombre,
                ImagenMiniatura = s.Imagen,
                Publicaciones = _eventPlusContext.Publicaciones.Where(s => s.IdEvento == s.Id).ToList()
            }).FirstOrDefault();

            return evento;
        }

        public ResponseViewModel Uptade(EventoViewModel model)
        {
            ResponseViewModel reponse = new ResponseViewModel();

            try
            {
                var eventExist = _eventPlusContext.Evento.Where(w => w.Id == model.Id).FirstOrDefault();

                if (eventExist == null)
                {
                    reponse.Type = "error";
                    reponse.Response = "El evento no existe ";
                    return reponse;
                }
                eventExist.IdTipo = model.IdTipo;
                eventExist.Imagen = model.ImagenMiniatura;
                eventExist.Nombre = model.Nombre;
                eventExist.Descripcion = model.Descripcion;
                eventExist.FechaInicio = model.FechaInicio;
                eventExist.FechaFin = model.FechaFin;

                _eventPlusContext.Evento.Add(eventExist);
                _eventPlusContext.Entry(eventExist).State = Microsoft.EntityFrameworkCore.EntityState.Modified;
                _eventPlusContext.SaveChanges();

               reponse.Type = "success";
                reponse.Response = "El regitsro se creo ha actulizado.";

                return reponse;
            }
            catch (Exception ex)
            {
                reponse.Type = "error";
                reponse.Response = "Error en el procedimiento. ---> " + ex.Message;
                return reponse;
            }
        }
    
    
    
    }
}
