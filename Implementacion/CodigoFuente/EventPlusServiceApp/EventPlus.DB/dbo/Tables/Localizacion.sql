CREATE TABLE [dbo].[Localizacion] (
    [Id]         INT           NOT NULL,
    [Id_Evento]  INT           NOT NULL,
    [Latitud]    DECIMAL (20)  NOT NULL,
    [Longitud]   DECIMAL (20)  NOT NULL,
    [Direccion]  VARCHAR (100) NULL,
    [Comentario] VARCHAR (100) NULL,
    [Activo]     CHAR (1)      DEFAULT ((1)) NOT NULL,
    CONSTRAINT [PK_Localizacion] PRIMARY KEY CLUSTERED ([Id] ASC)
);


GO
EXECUTE sp_addextendedproperty @name = N'MS_Description', @value = 'Tabla que contiene la información de la Localización', @level0type = N'SCHEMA', @level0name = N'dbo', @level1type = N'TABLE', @level1name = N'Localizacion';


GO
EXECUTE sp_addextendedproperty @name = N'MS_Description', @value = 'Id de la tabla', @level0type = N'SCHEMA', @level0name = N'dbo', @level1type = N'TABLE', @level1name = N'Localizacion', @level2type = N'COLUMN', @level2name = N'Id';


GO
EXECUTE sp_addextendedproperty @name = N'MS_Description', @value = 'Llave Foránea', @level0type = N'SCHEMA', @level0name = N'dbo', @level1type = N'TABLE', @level1name = N'Localizacion', @level2type = N'COLUMN', @level2name = N'Id_Evento';


GO
EXECUTE sp_addextendedproperty @name = N'MS_Description', @value = 'Latitud de la georeferenciación de googlemaps', @level0type = N'SCHEMA', @level0name = N'dbo', @level1type = N'TABLE', @level1name = N'Localizacion', @level2type = N'COLUMN', @level2name = N'Latitud';


GO
EXECUTE sp_addextendedproperty @name = N'MS_Description', @value = 'Longitud de la georeferenciación de googlemaps
Longitud de la georeferenciación de googlemaps', @level0type = N'SCHEMA', @level0name = N'dbo', @level1type = N'TABLE', @level1name = N'Localizacion', @level2type = N'COLUMN', @level2name = N'Longitud';


GO
EXECUTE sp_addextendedproperty @name = N'MS_Description', @value = 'Descripcion de la ubicacion o la dirección', @level0type = N'SCHEMA', @level0name = N'dbo', @level1type = N'TABLE', @level1name = N'Localizacion', @level2type = N'COLUMN', @level2name = N'Direccion';


GO
EXECUTE sp_addextendedproperty @name = N'MS_Description', @value = 'Descripcion de la ubicacion o la dirección', @level0type = N'SCHEMA', @level0name = N'dbo', @level1type = N'TABLE', @level1name = N'Localizacion', @level2type = N'COLUMN', @level2name = N'Comentario';


GO
EXECUTE sp_addextendedproperty @name = N'MS_Description', @value = 'Si activo en el sistema', @level0type = N'SCHEMA', @level0name = N'dbo', @level1type = N'TABLE', @level1name = N'Localizacion', @level2type = N'COLUMN', @level2name = N'Activo';

