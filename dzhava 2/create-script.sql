CREATE DATABASE DbForVideoGames
GO

USE DbForVideoGames
GO

IF OBJECT_ID ('dbo.video_games', 'U') IS NOT NULL  
   DROP TABLE video_games;  
GO  

CREATE TABLE video_games(
id INT PRIMARY KEY IDENTITY(1,1),
game_name NVARCHAR(30) NOT NULL,
game_description NVARCHAR(100) NOT NULL,
publisher NVARCHAR(20) NOT NULL,
developer NVARCHAR(20) NOT NULL,
writer NVARCHAR(20) NOT NULL,
category NVARCHAR(20) NOT NULL,
initial_release_date DATE NOT NULL,
price FLOAT NOT NULL,
is_deleted BIT NOT NULL DEFAULT 0
);
GO




