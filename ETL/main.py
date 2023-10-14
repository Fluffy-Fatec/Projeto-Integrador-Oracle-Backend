from etl.etl_process import ETLProcess

if __name__ == "__main__":
    etl = ETLProcess('config.yaml')
    tables_config = etl.config['tables']

    for table_config in tables_config:
        etl.run_etl_for_table(table_config)
