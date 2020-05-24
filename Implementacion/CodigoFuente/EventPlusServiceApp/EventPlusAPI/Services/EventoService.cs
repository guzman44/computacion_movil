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

               model.FechaFinG = DateTime.Parse(model.FechaFin);
               model.FechaInicioG = DateTime.Parse(model.FechaInicio);

                Evento ev = new Evento
                {
                    Nombre = model.Nombre,  
                    
                    Descripcion = model.Descripcion,
                    Imagen = model.ImagenMiniatura,
                    FechaInicio = model.FechaInicioG,
                    FechaFin = model.FechaFinG,
                    Activo = "1",
                    IdTipo = model.IdTipo,
                    FechaRegistro = DateTime.Now
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

        public ResponseViewModel CreateLike(int idEvent, int idLogin)
        {
            ResponseViewModel reponse = new ResponseViewModel();

            try
            {
                var categoriaLike = _eventPlusContext.ParametrizacionObjetos.Where(s => s.Nombre == "CAT_HISTORICO" && s.Valor == "Like").FirstOrDefault();
                Categoria cat = new Categoria
                {
                   IdCategoria = categoriaLike.Id,
                   IdEvento = idEvent,
                   IdLogin = idLogin,
                   FechaRegistro = DateTime.Now,
                   Activo = "1"                   
                };
                _eventPlusContext.Categoria.Add(cat);
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

        public ResponseViewModel DeleteLike(int idEvent, int idLogin)
        {
            ResponseViewModel reponse = new ResponseViewModel();

            try
            {
                var categoriaLike = _eventPlusContext.ParametrizacionObjetos.Where(s => s.Nombre == "CAT_HISTORICO" && s.Valor == "Like").FirstOrDefault();
                var eliminar = _eventPlusContext.Categoria.Where(w => w.IdCategoria == categoriaLike.Id && w.IdEvento == idEvent && w.IdLogin == idLogin).FirstOrDefault();

                _eventPlusContext.Categoria.Remove(eliminar);
                _eventPlusContext.Entry(eliminar).State = Microsoft.EntityFrameworkCore.EntityState.Modified;
                _eventPlusContext.SaveChanges();

                return reponse;
            }
            catch (Exception ex)
            {
                reponse.Type = "error";
                reponse.Response = "Error en el procedimiento. ---> " + ex.Message;
                return reponse;
            }
        }

        public ResponseViewModel DeleteLocation(int idEvent, int idLogin)
        {
            ResponseViewModel reponse = new ResponseViewModel();

            try
            {
                var eliminar = _eventPlusContext.Localizacion.Where(w => w.IdEvento == idEvent).FirstOrDefault();

                _eventPlusContext.Localizacion.Remove(eliminar);
                _eventPlusContext.Entry(eliminar).State = Microsoft.EntityFrameworkCore.EntityState.Modified;
                _eventPlusContext.SaveChanges();

                return reponse;
            }
            catch (Exception ex)
            {
                reponse.Type = "error";
                reponse.Response = "Error en el procedimiento. ---> " + ex.Message;
                return reponse;
            }
        }

        public ResponseViewModel DeletePublication(int idEvent, int idLogin)
        {
            ResponseViewModel reponse = new ResponseViewModel();

            try
            {
                var eliminar = _eventPlusContext.Publicaciones.Where(w => w.IdEvento == idEvent && w.IdLogin == idLogin).FirstOrDefault();

                _eventPlusContext.Publicaciones.Remove(eliminar);
                _eventPlusContext.Entry(eliminar).State = Microsoft.EntityFrameworkCore.EntityState.Modified;
                _eventPlusContext.SaveChanges();

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
                FechaRegistro = s.IdEventoNavigation.FechaRegistro
            }).ToList();

            return listEvent;
        }

        public AllEventoViewModel GetAllEvento(long idEvento)
        {
            var evento = _eventPlusContext.Evento.Where(w => w.Id == idEvento).Select(s => new AllEventoViewModel
            {
                Id = s.Id,
                IdLogin = _eventPlusContext.EventoUsuario.Where(p => p.IdEvento == idEvento).Select(p => p.IdLogin).FirstOrDefault(),
                Nombre = s.Nombre,
                Descripcion = s.Descripcion,
                FechaFin = s.FechaFin,
                FechaInicio = s.FechaInicio,
                IdTipo = s.IdTipo,
                Tipo = s.IdTipoNavigation.Nombre,
                ImagenMiniatura = s.Imagen,
                Galeria = _eventPlusContext.Galeria.Where(p => p.IdEvento == p.Id).ToList(),
                Localizacion = _eventPlusContext.Localizacion.Where(p => p.IdEvento == s.Id).ToList(),
                Publicaciones = _eventPlusContext.Publicaciones.Where(p => p.IdEvento == s.Id).ToList(),
                FechaRegistro = s.FechaRegistro
            }).FirstOrDefault();

            if(evento!= null)
            {
                var usuario = _eventPlusContext.Usuario.Where(w => w.IdLogin == evento.IdLogin).FirstOrDefault();
                var nombre = (usuario.Nombres + " " + usuario.Apellidos).Trim();

                evento.NombreUsuario = (nombre != null && !nombre.Equals("")) ? usuario.IdLoginNavigation.UserName : nombre;
                var categoriaLike = _eventPlusContext.ParametrizacionObjetos.Where(s => s.Nombre == "CAT_HISTORICO" && s.Valor == "Like").FirstOrDefault();
                evento.Likes = _eventPlusContext.Categoria.Where(w => w.IdCategoria == categoriaLike.Id && w.IdEvento == idEvento).Count();
            }
            else
            {
                evento = new AllEventoViewModel();  
            }  

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
                Galeria = _eventPlusContext.Galeria.Where(s => s.IdEvento == s.Id).ToList(),
                FechaRegistro = s.FechaRegistro
            }).FirstOrDefault();

            if(evento!= null)
            {
                var usuario = _eventPlusContext.Usuario.Where(w => w.IdLogin == evento.IdLogin).FirstOrDefault();
                var nombre = (usuario.Nombres + " " + usuario.Apellidos).Trim();

                evento.NombreUsuario = (nombre != null && !nombre.Equals("")) ? usuario.IdLoginNavigation.UserName : nombre;
                var categoriaLike = _eventPlusContext.ParametrizacionObjetos.Where(s => s.Nombre == "CAT_HISTORICO" && s.Valor == "Like").FirstOrDefault();
                evento.Likes = _eventPlusContext.Categoria.Where(w => w.IdCategoria == categoriaLike.Id && w.IdEvento == idEvento).Count();
            }
            else
            {
                evento = new GalleryEventoViewModel();
            }


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
                Localizacion = _eventPlusContext.Localizacion.Where(s => s.IdEvento == s.Id).ToList(),
                FechaRegistro = s.FechaRegistro
            }).FirstOrDefault();

            if (evento!= null)
            {
                var usuario = _eventPlusContext.Usuario.Where(w => w.IdLogin == evento.IdLogin).FirstOrDefault();
                var nombre = (usuario.Nombres + " " + usuario.Apellidos).Trim();

                evento.NombreUsuario = (nombre != null && !nombre.Equals("")) ? usuario.IdLoginNavigation.UserName : nombre;
                var categoriaLike = _eventPlusContext.ParametrizacionObjetos.Where(s => s.Nombre == "CAT_HISTORICO" && s.Valor == "Like").FirstOrDefault();
                evento.Likes = _eventPlusContext.Categoria.Where(w => w.IdCategoria == categoriaLike.Id && w.IdEvento == idEvento).Count();

            }
            else
            {
                evento = new LocationEventoViewModel();
            }
            
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
                Publicaciones = _eventPlusContext.Publicaciones.Where(s => s.IdEvento == s.Id).ToList(),
                FechaRegistro = s.FechaRegistro
            }).FirstOrDefault();

            if (evento != null)
            {
                var usuario = _eventPlusContext.Usuario.Where(w => w.IdLogin == evento.IdLogin).FirstOrDefault();
                var nombre = (usuario.Nombres + " " + usuario.Apellidos).Trim();

                evento.NombreUsuario = (nombre != null && !nombre.Equals("")) ? usuario.IdLoginNavigation.UserName : nombre;
                var categoriaLike = _eventPlusContext.ParametrizacionObjetos.Where(s => s.Nombre == "CAT_HISTORICO" && s.Valor == "Like").FirstOrDefault();
                evento.Likes = _eventPlusContext.Categoria.Where(w => w.IdCategoria == categoriaLike.Id && w.IdEvento == idEvento).Count();
            }
            else
            {
                evento = new PublicationEventoViewModel();
            }

            return evento;
        }

        public List<AllHistoryEventoViewModel> SearchEventAll(SearchEventAllViewModel model)
        {
            var listEvent = _eventPlusContext.Evento.Where(w => w.Nombre.Contains(model.Text)).Select(s => new AllHistoryEventoViewModel
            {
                Id = s.Id,
                IdLogin = _eventPlusContext.EventoUsuario.Where(w => w.IdEvento == s.Id).Select(w => w.IdLogin).FirstOrDefault(),
                Nombre = s.Nombre,
                Descripcion = s.Descripcion,
                FechaFin = s.FechaFin,
                FechaInicio = s.FechaInicio,
                IdTipo = s.IdTipo,
                Tipo = s.IdTipoNavigation.Nombre,
                ImagenMiniatura = s.Imagen,
                FechaRegistro = s.FechaRegistro
            }).ToList();

            return listEvent;
        }

        public List<AllHistoryEventoViewModel> SearchEventLogin(SearchEventLoginViewModel model)
        {
            var listEvent = _eventPlusContext.EventoUsuario.Where(w => w.IdLogin == model.IdLogin && w.IdEventoNavigation.Nombre.Contains(model.Text)).Select(s => new AllHistoryEventoViewModel
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
                FechaRegistro = s.IdEventoNavigation.FechaRegistro
            }).ToList();

            return listEvent;
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
