import oracledb

class ETLLoader:
    def __init__(self, target_connector):
        self.target_connector = target_connector


    def load_data(self, insert_query, data):
        try:
            dw_connection = self.target_connector.get_connection()
            cursor = dw_connection.cursor()

            for row in data:
                cursor.execute(insert_query, row)

            dw_connection.commit()
            print("Dados inseridos com sucesso no data warehouse.")
        except oracledb.DatabaseError as e:
            print(f"Erro ao inserir dados no data warehouse: {str(e)}")
