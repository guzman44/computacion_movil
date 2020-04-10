/*
Post-Deployment Script Template							
--------------------------------------------------------------------------------------
 This file contains SQL statements that will be appended to the build script.		
 Use SQLCMD syntax to include a file in the post-deployment script.			
 Example:      :r .\myfile.sql								
 Use SQLCMD syntax to reference a variable in the post-deployment script.		
 Example:      :setvar TableName MyTable							
               SELECT * FROM [$(TableName)]					
--------------------------------------------------------------------------------------
*/


USE [EventPlus]
GO

INSERT INTO [dbo].[Evento]([Nombre],[Descripcion],[Imagen],[FechaInicio],[FechaFin],[Activo])
VALUES ('Evento de prueba','Este es el primer evento',null, GETDATE(),DATEADD(DAY,5, GETDATE())
           ,1)
GO