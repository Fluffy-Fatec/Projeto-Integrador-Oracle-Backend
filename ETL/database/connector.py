# database/connector.py

import oracledb

class DatabaseConnector:
    def __init__(self, config):
        self.config = config
        self.connection = None

    def connect(self):
        try:
            self.connection = oracledb.connect(
                user=self.config['user'],
                password=self.config['password'],
                dsn=self.config['dsn'],
                config_dir=self.config['config_dir'],
                wallet_location=self.config['wallet_location'],
                wallet_password=self.config['wallet_password']
            )
            return True
        except oracledb.DatabaseError as e:
            print(f"Erro ao conectar ao banco de dados: {str(e)}")
            return False

    def close(self):
        if self.connection:
            self.connection.close()

    def get_connection(self):
        return self.connection
