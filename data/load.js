const fs = require('fs');

const load = async () =>  {
    const file = 'answer.json';

    fs.writeFileSync(file,"");
    const { Client } = require('pg');

    const client = new Client(
        {
            user: 'adminejtetcb',
            host: 'localhost',
            database: 'mythidb',
            password: 'demo123',
            port: 5432,
        }
    );

    await client.connect();

    const res = await client.query('select id,subject,content from answer where id > 1300');
    res.rows.forEach( (row) => {
        const index = {
            index : { _index: "questions",
                      _type : "_doc",
                      _id : row.id }
        }
        delete row.id;

        fs.appendFileSync(file,JSON.stringify(index) + '\n','UTF-8');
        fs.appendFileSync(file,JSON.stringify(row) + '\n','UTF-8');
    })
    
    await client.end();
}

load();