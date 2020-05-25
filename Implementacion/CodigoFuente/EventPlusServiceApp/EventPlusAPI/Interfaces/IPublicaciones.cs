using EventPlusAPI.Dao;
using System.Collections.Generic;
using EventPlusAPI.ViewModel;

namespace EventPlusAPI.Interfaces
{
    public interface IPublicaciones
    {
        List<PublicationViewModel> GetAll();
        List<NotificacionesViewModel> GetAllNotificaciones(int idLogin);
        ResponseViewModel CreateNotificacion(NotificacionesViewModel model);
        ResponseViewModel UptadeNotificacion(NotificacionesViewModel model);
        
    }
}
