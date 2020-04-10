CREATE TABLE [dbo].[Evento] (
    [Id]          INT           NOT NULL,
    [Nombre]      VARCHAR (100) NOT NULL,
    [Descripcion] VARCHAR (250) NOT NULL,
    [Imagen]      IMAGE         NULL,
    [FechaInicio] DATETIME      NOT NULL,
    [FechaFin]    DATETIME      NOT NULL,
    [Activo]      CHAR (1)      DEFAULT ((1)) NOT NULL,
    CONSTRAINT [PK_Evento] PRIMARY KEY CLUSTERED ([Id] ASC)
);


GO
EXECUTE sp_addextendedproperty @name = N'MS_Description', @value = 'Tabla que contiene la información de la Evento', @level0type = N'SCHEMA', @level0name = N'dbo', @level1type = N'TABLE', @level1name = N'Evento';


GO
EXECUTE sp_addextendedproperty @name = N'MS_Description', @value = 'Id de la tabla', @level0type = N'SCHEMA', @level0name = N'dbo', @level1type = N'TABLE', @level1name = N'Evento', @level2type = N'COLUMN', @level2name = N'Id';


GO
EXECUTE sp_addextendedproperty @name = N'MS_Description', @value = 'Nombre del evento', @level0type = N'SCHEMA', @level0name = N'dbo', @level1type = N'TABLE', @level1name = N'Evento', @level2type = N'COLUMN', @level2name = N'Nombre';


GO
EXECUTE sp_addextendedproperty @name = N'MS_Description', @value = 'Descripción del evento', @level0type = N'SCHEMA', @level0name = N'dbo', @level1type = N'TABLE', @level1name = N'Evento', @level2type = N'COLUMN', @level2name = N'Descripcion';


GO
EXECUTE sp_addextendedproperty @name = N'MS_Description', @value = 'Imagen en miniatura', @level0type = N'SCHEMA', @level0name = N'dbo', @level1type = N'TABLE', @level1name = N'Evento', @level2type = N'COLUMN', @level2name = N'Imagen';


GO
EXECUTE sp_addextendedproperty @name = N'MS_Description', @value = 'Fecha de Inicio del evento', @level0type = N'SCHEMA', @level0name = N'dbo', @level1type = N'TABLE', @level1name = N'Evento', @level2type = N'COLUMN', @level2name = N'FechaInicio';


GO
EXECUTE sp_addextendedproperty @name = N'MS_Description', @value = 'Fecha Fin', @level0type = N'SCHEMA', @level0name = N'dbo', @level1type = N'TABLE', @level1name = N'Evento', @level2type = N'COLUMN', @level2name = N'FechaFin';


GO
EXECUTE sp_addextendedproperty @name = N'MS_Description', @value = 'Si activo en el sistema', @level0type = N'SCHEMA', @level0name = N'dbo', @level1type = N'TABLE', @level1name = N'Evento', @level2type = N'COLUMN', @level2name = N'Activo';

