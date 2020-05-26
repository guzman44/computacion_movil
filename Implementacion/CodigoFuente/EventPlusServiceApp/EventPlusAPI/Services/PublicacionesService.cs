using EventPlusAPI.Dao;
using EventPlusAPI.Interfaces;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using EventPlusAPI.ViewModel;

namespace EventPlusAPI.Services
{
    public class PublicacionesService : IPublicaciones
    {
        public readonly EventPlusContext _eventPlusContext;

        public PublicacionesService(EventPlusContext _eventPlusContext)
        {
            this._eventPlusContext = _eventPlusContext;
        }


        public List<PublicationViewModel> GetAll()
        {
            List<PublicationViewModel> retorno = _eventPlusContext.Publicaciones.Select(s => new PublicationViewModel
            {
                Id = s.Id,
                IdLogin = s.IdLoginNavigation.Id,
                Nombre = _eventPlusContext.Usuario.Where(w => w.IdLogin == s.IdLoginNavigation.Id).Select(w => w.Nombres +  " " + w.Apellidos).FirstOrDefault(),
                UserName = s.IdLoginNavigation.UserName,
                Comentario = s.Comentario,
                FechaIngresoMostrar  = CreateDBDateTime(s.FechaIngreso.ToString()),
                Imagen = s.Imagen,
                Avatar = _eventPlusContext.Usuario.Where(w => w.IdLogin == s.IdLoginNavigation.Id).Select(w => w.Imagen).FirstOrDefault(),
                IdEvento = s.IdEvento,
                Evento = s.IdEventoNavigation.Nombre                
            }).ToList();

            return retorno;
        }

        public List<NotificacionesViewModel> GetAllNotificaciones(int idLogin)
        {
            List<NotificacionesViewModel> retorno = _eventPlusContext.Notificaciones.Select(s => new NotificacionesViewModel
            {
                Id = s.Id,
                IdLogin = s.IdLoginNavigation.Id,
                NombreUsuario = _eventPlusContext.Usuario.Where(s => s.IdLogin == s.IdLoginNavigation.Id).Select(s => s.Nombres + " " + s.Apellidos).FirstOrDefault(),
                UserName = s.IdLoginNavigation.UserName,
                Mensaje = s.Mensaje,
                FechaRegistroMostrar = CreateDBDateTime(s.FechaRegistro.ToString()),
                Entregado = s.Entregado,
                Titulo = s.Titulo,
                FechaRegistro = s.FechaRegistro,
                Activo = s.Activo
            }).ToList();

            return retorno;
        }

        public ResponseViewModel CreateNotificacion(NotificacionesViewModel model)
        {
            ResponseViewModel reponse = new ResponseViewModel();

            try
            {
                Notificaciones noti = new Notificaciones
                {
                    Entregado = "0",
                    Mensaje = model.Mensaje,
                    Titulo = model.Titulo,
                    IdLogin = model.IdLogin,
                    Activo = "1",
                    FechaRegistro = DateTime.Now
                };
                _eventPlusContext.Notificaciones.Add(noti);
                _eventPlusContext.SaveChanges();

                reponse.Type = "success";
                reponse.Response = "El regitsro se creó exitosamente.";

                return reponse;
            }
            catch (Exception ex)
            {
                reponse.Type = "error";
                reponse.Response = "Error en el procedimiento. ---> " + ex.Message;
                return reponse;
            }
        }

        public ResponseViewModel UptadeNotificacion(NotificacionesViewModel model)
        {
            ResponseViewModel reponse = new ResponseViewModel();

            try
            {
                var notiExi = _eventPlusContext.Notificaciones.Where(w => w.Id == model.Id).FirstOrDefault();

                if (notiExi == null)
                {
                    reponse.Type = "error";
                    reponse.Response = "La notificación no existe ";
                    return reponse;
                }
                notiExi.Entregado = "1";

                _eventPlusContext.Notificaciones.Add(notiExi);
                _eventPlusContext.Entry(notiExi).State = Microsoft.EntityFrameworkCore.EntityState.Modified;
                _eventPlusContext.SaveChanges();

                reponse.Type = "success";
                reponse.Response = "El regitsro se ha actulizado.";

                return reponse;
            }
            catch (Exception ex)
            {
                reponse.Type = "error";
                reponse.Response = "Error en el procedimiento. ---> " + ex.Message;
                return reponse;
            }
        }

        public static string CreateDBDateTime(string date)
        {
            DateTime result;
            if (DateTime.TryParse(date, out result))
            {
                return result.ToString("dd/MM/yyyy - HH:mm:ss", System.Globalization.CultureInfo.InvariantCulture);
            }
            return "";
        }        
    }
}
