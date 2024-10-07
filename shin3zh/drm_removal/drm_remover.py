import os
import pathlib
import shutil
import zipfile
import tqdm


def input_with_varify(desc: str = None):
    # 带有验证的输入
    in_flag = True
    try:
        in_str = input(desc)
        in_path = pathlib.Path(in_str).resolve()
        if in_path.exists() and in_str.lower().endswith('.epub'):
            in_flag = False
    except:
        pass
    if in_flag:
        print('指定的文件无效！\n请重新输入。')
        return input_with_varify(desc)
    return str(in_path)


def print_info():
    # 显示脚本文件信息
    print('**********************************************\n'
          '*                                            *\n'
          '*           EPUB伪DRM去除工具  v1.0          *\n'
          '*                                            *\n'
          '*                                            *\n'
          '*                 作者：直心                 *\n'
          '*            日期：2022年12月03日            *\n'
          '*                                            *\n'
          '**********************************************\n'
          )


def unzip_epub(epubfilename: str, tmp_dir: str):
    # 解压缩并重命名
    file_num = 0
    with zipfile.ZipFile(epubfilename, 'r') as zfile:
        nl = zfile.namelist()
        for l in tqdm.tqdm(nl, desc='正在解压epub文件', leave=False):
            outpath = os.path.join(tmp_dir, l.replace('*', '0').replace(':', '1'))
            z = zfile.read(l)
            os.makedirs(os.path.dirname(outpath), exist_ok=True)
            with open(outpath, 'wb') as f:
                f.write(z)
            file_num += 1
    return file_num


def fix_epub(tmp_dir: str, file_num: int):
    # 替换被扰乱的文件名
    encryption_mark_path = os.path.join(tmp_dir, 'META-INF/encryption.xml')
    if os.path.exists(encryption_mark_path):
        os.remove(encryption_mark_path)
    file_names_old = []
    file_names_old_url = []
    file_names_old_url_raw = []
    file_names_new = []
    file_paths = []
    with tqdm.tqdm(total=file_num, desc='正在准备去除drm保护', leave=False) as show:
        for dirpath, dirnames, filenames in os.walk(tmp_dir):
            for filepath in filenames:
                file_names_old.append(filepath.replace('0', '*').replace('1', ':'))
                file_names_old_url.append(filepath.replace('0', '%2A').replace('1', '%3A'))
                file_names_old_url_raw.append(filepath.replace('0', '*').replace('1', ':'))
                file_names_new.append(filepath)
                # print(filepath.replace('0', '*').replace('1', ':'))
                file_paths.append(os.path.join(dirpath, filepath))
                show.update()
    with tqdm.tqdm(total=file_num, desc='正在去除drm保护', leave=False) as show:
        for file_path in file_paths:
            if file_path.endswith('.xhtml') \
                    or file_path.endswith('.ncx')\
                    or file_path.endswith('.css')\
                    or file_path.endswith('.opf')\
                    or file_path.endswith('.html'):
                # print(file_path)
                with open(file_path, 'r', encoding='utf-8') as f:
                    lines = f.readlines()
                # print(len(lines))
                new_lines = []
                for line in lines:
                    for fname_old, fname_old_url, fnames_old_url_raw, fname_new in zip(file_names_old, file_names_old_url, file_names_old_url_raw, file_names_new):
                        line = line.replace(fname_old, fname_new).replace(fname_old_url, fname_new).replace(fnames_old_url_raw, fname_new)
                    new_lines.append(line)
                with open(file_path, 'w', encoding='utf-8') as f:
                    f.writelines(new_lines)
            show.update()


def save_new_epub(tmp_dir: str, output_path: str, file_num: int):
    # 生成新的epub文件
    # print("原始文件夹路径：" + dirpath)
    parent_path = tmp_dir
    # print("压缩文件夹目录：", parent_name)
    with tqdm.tqdm(total=file_num, desc='正在生成新epub文件', leave=False) as show:
        with zipfile.ZipFile(output_path, "w", zipfile.ZIP_DEFLATED) as zip:
            # 多层级压缩
            for root, dirs, files in os.walk(tmp_dir):
                for file in files:
                    if str(file).startswith("~$codeholder_0&"):
                        continue
                    filepath = os.path.join(root, file)
                    # print("压缩文件路径：" + filepath)
                    writepath = os.path.relpath(filepath, parent_path)
                    zip.write(filepath, writepath)
                    show.update()


def main():
    print_info()
    tmp_dir = r'no_drm_epub'
    if os.path.exists(tmp_dir):
        shutil.rmtree(tmp_dir)
    epubfilename = input_with_varify('\n请输入要去除保护的EPUB文件：\n')
    file_num = unzip_epub(epubfilename, tmp_dir)
    fix_epub(tmp_dir, file_num)
    output_path = os.path.splitext(epubfilename)[0]+'_已经去除DRM.epub'
    save_new_epub(tmp_dir, output_path, file_num)
    shutil.rmtree(tmp_dir)
    print('处理完成！新的epub文件为 “{}”'.format(output_path))


if __name__ == '__main__':
    main()
