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

        public ResponseViewModel Create(CreateEventoViewModel model)
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

        public ResponseViewModel CreateGallery(CreateGalleryViewModel model)
        {
            ResponseViewModel reponse = new ResponseViewModel();

            try
            {
                Galeria gallery = new Galeria
                {
                  IdEvento = model.IdEvento ,
                  Image = model.Image
                };
                _eventPlusContext.Galeria.Add(gallery);
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

        public ResponseViewModel CreateGalleryAll(List<CreateGalleryViewModel> model)
        {
            ResponseViewModel reponse = new ResponseViewModel();

            try
            {
                foreach (var lista in model)
                {
                    Galeria gallery = new Galeria
                    {
                        IdEvento = lista.IdEvento,
                        Image = lista.Image
                    };
                    _eventPlusContext.Galeria.Add(gallery);
                    _eventPlusContext.SaveChanges();
                }

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

        public ResponseViewModel CreateLocation(CreateLocationViewModel model)
        {
            ResponseViewModel reponse = new ResponseViewModel();

            try
            {
                Localizacion localization = new Localizacion
                {
                    IdEvento = model.IdEvento,
                    Activo = "1",
                    Comentario = model.Comentario,
                    Direccion = model.Direccion,
                    Latitud = model.Latitud,
                    Longitud = model.Longitud
                };
                _eventPlusContext.Localizacion.Add(localization);
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

        public ResponseViewModel CreateLocationAll(List<CreateLocationViewModel> model)
        {
            ResponseViewModel reponse = new ResponseViewModel();

            try
            {
                foreach(var lista in model)
                {
                    Localizacion localization = new Localizacion
                    {
                        IdEvento = lista.IdEvento,
                        Activo = "1",
                        Comentario = lista.Comentario,
                        Direccion = lista.Direccion,
                        Latitud = lista.Latitud,
                        Longitud = lista.Longitud
                    };
                    _eventPlusContext.Localizacion.Add(localization);
                    _eventPlusContext.SaveChanges();
                }

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

        public ResponseViewModel CreatePublication(CreatePublicationViewModel model)
        {
            ResponseViewModel reponse = new ResponseViewModel();

            try
            {
                Publicaciones publication = new Publicaciones
                {
                    IdEvento = model.IdEvento,
                    Activo = "1",
                    Comentario = model.Comentario,
                    FechaIngreso = DateTime.Now,
                    IdLogin = model.IdLogin,
                    Imagen = model.Imagen
                };
                _eventPlusContext.Publicaciones.Add(publication);
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

        public List<AllHistoryEventoViewModel> GetAll(long idLogin)
        {
            var listEvent = _eventPlusContext.EventoUsuario.Where(w => w.IdLogin == idLogin).Select(s => new AllHistoryEventoViewModel
            {
                Id = s.IdEventoNavigation.Id,
                IdLogin = s.IdLogin,
                Nombre = s.IdEventoNavigation.Nombre,
                Descripcion = s.IdEventoNavigation.Descripcion,
                FechaFin = s.IdEventoNavigation.FechaFin,
                FechaInicio = s.IdEventoNavigation.FechaInicio,
                IdTipo = s.IdEventoNavigation.IdTipo,
                Tipo = s.IdEventoNavigation.IdTipoNavigation.Nombre,
                ImagenMiniatura = s.IdEventoNavigation.Imagen,
                //FechaRegistro = s.
            }).ToList();

            return listEvent;
        }

        public AllEventoViewModel GetAllEvento(long idEvento)
        {
            var evento = _eventPlusContext.Evento.Where(w => w.Id == idEvento).Select(s => new AllEventoViewModel
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
                Publicaciones = _eventPlusContext.Publicaciones.Where(s => s.IdEvento == s.Id).ToList(),
               // FechaRegistro = 
            }).FirstOrDefault();

            return evento;
        }

        public GalleryEventoViewModel GetAllGaleriaxEvento(long idEvento)
        {
            var evento = _eventPlusContext.Evento.Where(w => w.Id == idEvento).Select(s => new GalleryEventoViewModel
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

        public LocationEventoViewModel GetAllLocalizacionxEvento(long idEvento)
        {
            var evento = _eventPlusContext.Evento.Where(w => w.Id == idEvento).Select(s => new LocationEventoViewModel
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

        public PublicationEventoViewModel GetAllPublicacionxEvento(long idEvento)
        {
            var evento = _eventPlusContext.Evento.Where(w => w.Id == idEvento).Select(s => new PublicationEventoViewModel
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

        public ResponseViewModel Uptade(UpdateEventoViewModel model)
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
